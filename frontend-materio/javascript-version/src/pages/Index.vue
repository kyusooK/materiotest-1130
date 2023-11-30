<template>
  <v-col :cols="2">
    <DrawerContent/>
  </v-col>
  <v-app id="inspire">
    <div>
      <v-app-bar app clipped-left flat color="#F4F5FA">
        <v-toolbar-title>
          <span class="second-word font uppercase contrast-primary-text"
            style="font-weight:700;"
          >{{ projectName }}
          </span>
        </v-toolbar-title>
        <span v-if="urlPath!=null" 
          class="mdi mdi-home contrast-primary-text"
          key="" 
          to="/" 
          @click="goHome()"
          style="margin-left:10px; font-size:20px; cursor:pointer;"
        ></span> 
        <v-spacer></v-spacer>
        <b style="margin-left:10px; font-size:10px;"
            class="contrast-primary-text"
        >{{username}} 님</b>
        <v-btn
            text
            class="contrast-primary-text"
            style="font-size:10px"
            @click="logout()"
        >Logout
        </v-btn>
      </v-app-bar>
    </div>
    <v-col cols="10" style="margin: 0px; padding: 0px;">
      <v-main>
        <v-container v-if="urlPath" style="max-width:100vw !important;" class="py-8 px-6 mt-10" fluid>
          <router-view></router-view>
        </v-container>
        <v-container v-else class="py-8 px-6 mt-10" fluid>
          <v-row>
            <v-card
                v-for="(card, index) in cards"
                :key="index"
                class="mx-auto"
                style="height:300px; width:300px; margin-bottom:20px;"
                outlined
            >
              <v-list-item>
                      <v-icon style="margin-top: 30%; margin-left: 37.5%;" color="primary" size="64">mdi-apps</v-icon>
              </v-list-item>
              <v-card-actions>
                <v-tooltip bottom>
                  <template v-slot:activator="{ on }">
                      <v-btn
                          v-on="on"
                          class="mx-auto"
                          outlined
                          rounded
                          :to="card.link"
                          @click="changeUrl()"
                          style="font-weight:500; font-size:20px; border:solid 2px; max-width:250px; overflow:hidden; margin-top: 10%;"
                      >
                          {{ card.text }}
                      </v-btn>
                  </template>
                  <span>{{ card.text }}</span>
                </v-tooltip>
              </v-card-actions>
            </v-card>
          </v-row>
        </v-container>
      </v-main>
    </v-col>
  </v-app>
</template>

<script>
import { ref, onMounted, computed } from 'vue';
import DrawerContent from '../layouts/components/DrawerContent.vue'

export default {
  name: 'App',
  components:{
    DrawerContent
  },
  data: () => ({
    useComponent: "",
    drawer: true,
    components: {},
    sideBar: true,
    urlPath: null,
    menus: [
            { id: 'basic', title: 'Basic',
                items: [
                    { key: 'companies', url: '/companies', name: 'Company' },
                ]
            },
            { id: 'sales', title: 'Sales',
                items: [
                    { key: 'salesOrders', url: '/salesOrders', name: 'SalesOrder' },
                ]
            },
        ],
        cards: [
            {
                text: "Company",
                link: "/companies",
            },
            {
                text: "SalesOrder",
                link: "/salesOrders",
            },
        ],
    activeMenu: null,
  }),

  async created() {
    var path = document.location.href.split("#/")
    this.urlPath = path[1];
    },

  mounted() {
    var me = this;
    me.components = this.$ManagerLists;
  },
  computed: {
      activeMenuItems() {
        const activeMenu = this.menus.find(menu => menu.id === this.activeMenu);
        return activeMenu ? activeMenu.items : [];
      }
  },
  methods: {
    changeMenu(menuId) {
      this.activeMenu = this.activeMenu === menuId ? null : menuId;
    },
    changePath(event) {
      let targetUrl = event.currentTarget.getAttribute('data-to');
      this.$router.push(targetUrl);
    },
    openSideBar(){
      this.sideBar = !this.sideBar
    },
    changeUrl() {
      var path = document.location.href.split("#/")
      this.urlPath = path[1];
    },
    goHome() {
      this.urlPath = null;
    },
  }
};

</script>