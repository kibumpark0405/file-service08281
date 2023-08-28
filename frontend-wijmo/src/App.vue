<template>
    <v-app id="inspire">
        <div>
            <Snackbar/>
            <v-app-bar app clipped-left flat color="primary">
                <v-toolbar-title>
                    <v-app-bar-nav-icon
                        @click="openSideBar()"
                        style="font-size:56px; font-weight: 300; padding-top:5px; margin-right:5px;"
                    ><div class="main-text-color">≡</div>
                    </v-app-bar-nav-icon>
                    <span class="second-word font uppercase main-text-color"
                        style="font-weight:700;"
                    >google drive
                    </span>
                </v-toolbar-title>
                <span v-if="urlPath!=null" 
                    class="mdi mdi-home main-text-color" 
                    key="" 
                    to="/" 
                    @click="goHome()"
                    style="margin-left:10px; font-size:20px; cursor:pointer;"
                    ></span> 
                <v-spacer></v-spacer>

                <b class="main-text-color"
                    style="margin-left:10px; font-size:10px;"
                >{{username}} 님</b>
                <v-btn
                    text
                    color="mainText"
                    style="font-size:10px"
                    @click="logout()"
                >
                    Logout
                </v-btn>
            </v-app-bar>

            <v-navigation-drawer app clipped flat v-model="sideBar">
                <v-list>
                    <v-list-group
                        v-for="menu in menus"
                        :key="menu.id"
                        no-action
                    >
                        <template v-slot:activator>
                            <v-list-item-content>
                                <v-list-item-title>{{ menu.title }}</v-list-item-title>
                            </v-list-item-content>
                        </template>

                        <v-list-item
                            v-for="item in menu.items"
                            :key="item.key"
                            :to="item.url"
                            @click="changeUrl()"
                        >
                            <v-list-item-content>
                                <v-list-item-title>{{ item.name }}</v-list-item-title>
                            </v-list-item-content>
                        </v-list-item>
                    </v-list-group>
                </v-list>
            </v-navigation-drawer>
        </div>

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
                            <v-list-item-avatar class="mx-auto" size="80" style="margin-top:80px;">
                                <span class="mdi mdi-apps primary-color" style="font-size:60px;"></span>
                            </v-list-item-avatar>
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
                                    style="font-weight:500; font-size:20px; padding:15px; border:solid 2px; max-width:250px; overflow:hidden"
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
    </v-app>
</template>

<script>
import BaseGrid from './components/base-ui/BaseGrid'

export default {
    name: "App",
    mixins:[BaseGrid],
    data: () => ({
        useComponent: "",
        drawer: true,
        components: {},
        sideBar: true,
        urlPath: null,
        menus: [
            { id: 'drive', title: '드라이브',
                items: [
                    { key: 'files', url: '/files', name: '파일' },
                ]
            },
            { id: 'indexer', title: '인덱서',
                items: [
                    { key: 'indices', url: '/indices', name: '인덱스' },
                ]
            },
            { id: 'videoProcessing', title: '비디오 처리',
                items: [
                    { key: 'videos', url: '/videos', name: '비디오' },
                ]
            },
            { id: 'notification', title: '알림',
                items: [
                    { key: 'notifications', url: '/notifications', name: '알림' },
                ]
            },
            { id: 'dashboard', title: '대시보드',
                items: [
                    { key: 'fileStatuses', url: '/fileStatuses', name: '파일 상태' },
                ]
            },
        ],
        cards: [
            {
                text: "파일",
                link: "/files",
            },
            {
                text: "인덱스",
                link: "/indices",
            },
            {
                text: "비디오",
                link: "/videos",
            },
            {
                text: "알림",
                link: "/notifications",
            },
            {
                text: "파일 상태",
                link: "/fileStatuses",
            },
        ],
        activeMenu: null
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
<style>

</style>
