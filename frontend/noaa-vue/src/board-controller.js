import 'es6-promise/auto'
import Vue from 'vue';
import VueRouter from 'vue-router';
import Home from './components/home.vue';
import Detail from './components/detail.vue';
import Write from './components/write.vue';
import Update from './components/update.vue';
import Join from './components/join.vue';
import Login from './components/login.vue';

Vue.use(VueRouter);

Vue.config.devtools = true;

const routes = [    
    { path: '/', component: Home, name: 'home' },
    { path: '/detail/:bno', component: Detail, name: 'detail' },
    { path: '/write', component: Write, name: 'write' },
    { path: '/update/:bno', component: Update, name: 'update' },
    { path: '/join', component: Join, name: 'join' },
    { path: '/login', component: Login, name: 'login' },
];

const router = new VueRouter({
    routes
})

const app = new Vue({
    router
}).$mount('#xp-container')