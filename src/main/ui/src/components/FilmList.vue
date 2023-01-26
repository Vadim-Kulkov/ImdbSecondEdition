<template>

  <v-app id="inspire">
    <v-app-bar
        app
        shrink-on-scroll
    >
<!--      <v-app-bar-nav-icon></v-app-bar-nav-icon>-->

      <v-toolbar-title>Movies</v-toolbar-title>

      <v-spacer></v-spacer>

<!--      <v-btn icon>-->
<!--        <v-icon>mdi-dots-vertical</v-icon>-->
<!--      </v-btn>-->
    </v-app-bar>

    <v-main>
      <v-container>
        <v-row>
          <v-col
              v-for="item in MovieDataList"
              :key="item"
              cols="3"
          >
            <v-card height="200" width="150" style="background-color: cornsilk">
              <v-card-title class="justify-center">
                <router-link :to="{name:'film', params: {id: item.id}}"> {{ item.name }}</router-link>
              </v-card-title>
              <v-img :src="require('@/assets/images/' + item.image)" alt=null position="center"/>
            </v-card>
          </v-col>
        </v-row>
      </v-container>
    </v-main>
  </v-app>
  <div>
    <v-parallax src="https://cdn.vuetifyjs.com/images/parallax/material.jpg"></v-parallax>
  </div>
</template>

<script>
export default {
  name: 'film-list',
  data() {
    return {
      MovieDataList: []
    };
  },
  methods: {
    getFilmList() {
      fetch('http://localhost:8080/films/all')
          .then(response => response.json())
          .then(data => (this.MovieDataList = data));
    }
  },
  beforeMount() {
    this.getFilmList()
  },
}
</script>
