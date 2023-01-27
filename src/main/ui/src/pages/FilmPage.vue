<template>
  <v-app-bar
      app
      shrink-on-scroll
  >
    <v-toolbar-title>{{ movie.name }}</v-toolbar-title>
  </v-app-bar>

  <div style="float:left">
    <br>
    <v-img style="border:6px solid #a590a6" :src="userWithImage.image" width="300"/>
  </div>

  <div class="text-board">
    <br>
    <h3>&nbsp;Producers</h3>
    <ul>
      <li v-for="person in producerDataList" v-bind:key="person"> &nbsp;&nbsp;{{ person.firstName }} {{
          person.patronymic
        }} {{ person.lastName }}
      </li>
    </ul>
    <br>
    <h3>&nbsp;Cast</h3>
    <ul>
      <li v-for="person in actorDataList" v-bind:key="person">&nbsp;&nbsp;{{ person.firstName }} {{ person.patronymic }}
        {{ person.lastName }}
      </li>
    </ul>
    <br>
    <h3>&nbsp;Country</h3> &nbsp;&nbsp;{{ movie.country.name }}
    <br>
    <br>
    <h3>&nbsp;Release date</h3> &nbsp;&nbsp; {{movie.releaseDate}}
    <br>
    <br>
    <h3>&nbsp;Description</h3>
    <p>&nbsp;&nbsp; {{ movie.description }}</p>
    <br>
  </div>
  <div class="input-text" id="textin">
    <h3>&nbsp;Create your own review</h3>
    <v-form v-model="valid">
      <v-container>
        <v-row>
          <v-col
              cols="12"
              md="4"
          >
            <v-text-field id="1"
                          v-model="firstName"
                          :counter="10"
                          label="First name"
                          required
            ></v-text-field>
            <v-text-field id="2"
                          v-model="title"
                          label="Title"
                          :counter="10"
                          required
            ></v-text-field>
            <v-textarea id="3"
                        v-model="description"
                        label="Description"
                        :counter="10"
                        required
            ></v-textarea>
          </v-col>

          <v-col
              cols="12"
              md="4"
          >
            <v-text-field id="4"
                          v-model="lastName"
                          :rules="nameRules"
                          :counter="10"
                          label="Last name"
                          required
            ></v-text-field>
            <br><br><br><br><br><br>
            <button type="button" style="border-bottom-style: outset" v-on:click="createReview">Create!</button>
          </v-col>
          <v-col
              cols="12"
              md="4"
          >
            <v-text-field id="5"
                          v-model="email"
                          :rules="emailRules"
                          label="E-mail"
                          required
            ></v-text-field>
          </v-col>
        </v-row>

      </v-container>
    </v-form>
  </div>
  <div id="app">
    <v-app>
      <h2>&nbsp;Reviews</h2>
      <v-expansion-panels focusable>
        <v-expansion-panel
            v-for="review in reviewDataList"
            :key="review"
        >
          <h4>&nbsp;{{ review.reviewer.firstName }} {{ review.reviewer.patronymic }}
            {{ review.reviewer.lastName }} - " {{ review.title }} "
            &nbsp;</h4>
          <p>
            &nbsp;{{ review.description }}
          </p>
          <br>
        </v-expansion-panel>
      </v-expansion-panels>
    </v-app>
  </div>
  <div>
    <v-parallax src="https://cdn.vuetifyjs.com/images/parallax/material.jpg"></v-parallax>
  </div>
</template>

<script>
export default {
  name: "film-page",
  data() {
    return {
      movie: {},
      currentId: 0,
      reviewDataList: [],
      actorDataList: [],
      producerDataList: [],
      length: 10,
      window: 0,
      valid: false,
      firstName: '',
      lastName: '',
      nameRules: [
        v => !!v || 'Name is required',
        v => v.length <= 10 || 'Name must be less than 10 characters',
      ],
      email: '',
      emailRules: [
        v => !!v || 'E-mail is required',
        v => /.+@.+/.test(v) || 'E-mail must be valid',
      ],
      title: '',
      description: '',
    };
  },
  methods: {
    getFilmData() {
      fetch('http://localhost:8080/films/' + parseInt(this.$route.params.id))
          .then(response => response.json())
          .then(data => (this.movie = data));
    },
    getReviewData() {
      fetch('http://localhost:8080/reviews/allByFilm/' + parseInt(this.$route.params.id))
          .then(response => response.json())
          .then(data => (this.reviewDataList = data));
    },
    getActorsData() {
      fetch('http://localhost:8080/persons/actorsByFilm/' + parseInt(this.$route.params.id))
          .then(response => response.json())
          .then(data => (this.actorDataList = data));
    },
    getProducersData() {
      fetch('http://localhost:8080/persons/producersByFilm/' + parseInt(this.$route.params.id))
          .then(response => response.json())
          .then(data => (this.producerDataList = data));
    },
    createReview() {
      const url = 'http://localhost:8080/reviews'
      var review =
          {
            "id": null,
            "filmId": this.movie.id,
            "reviewer": {
              "firstName": this.firstName,
              "lastName": this.lastName,
              "patronymic": null,
              "birthDate": null,
              "deathDate": null,
              "gender": null,
              "homeCountry": null,
              "biography": null
            },
            "title": this.title,
            "description": this.description
          }
      var options = {
        method: 'POST',
        body: JSON.stringify(review),
        headers: {
          'Content-Type': 'application/json'
        }
      }
      fetch(url, options)
          .then(res => res.json())
          .then(res => console.log(res))
    },
  },

  beforeMount() {
    this.currentId = this.$route.params.id
    this.getProducersData()
    this.getActorsData()
    this.getFilmData()
    this.getReviewData()
  }
  ,
  computed: {
    userWithImage() {
      return {
        ...this.movie,
        image: this.movie.image && require(`../assets/images/${this.movie.image}`)
      }
    }
  }
}
</script>

<style scoped>
.text-board {
  overflow: hidden;
  max-width: 75ch;
  max-lines: 10;
}
</style>
