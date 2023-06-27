import { createWebHistory, createRouter } from "vue-router";

const routes = [
    {
        name: 'Login',
        path: "/",
        component: () => import('../components/LoginView.vue'),
    },
    {
        name: 'Welcome',
        path: "/welcome",
        component: () => import('../components/WelcomeView.vue'),
    },
    {
        name: 'Home',
        path: "/home",
        component: () => import('../components/HomeView.vue'),
    },
    {
        name: 'CheckIn',
        path: "/checkIn",
        component: () => import('../components/CheckInView.vue'),
    },
    {
        name: 'Emp',
        path: "/emp",
        component: () => import('../components/EmpView.vue'),
    },
    {
        name: 'SunTest',
        path: "/sunTest",
        component: () => import('../components/SunTestView.vue'),
    }

]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes: routes,
    linkActiveClass: 'active'
})



export default router;