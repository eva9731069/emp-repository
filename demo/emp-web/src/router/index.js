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
    },
    {
        name: 'Add',
        path: "/add",
        component: () => import('../components/AddView.vue'),
    },
    {
        name: 'Edit',
        path: "/edit",
        component: () => import('../components/EditView.vue'),
    },
    {
        name: 'JqueryTest',
        path: "/jqueryTest",
        component: () => import('../components/JqueryView.vue'),
    },
    {
        name: 'JqueryEdit',
        path: "/jqueryEdit",
        component: () => import('../components/EditJqueryView.vue'),
    },
    {
        name: 'Function',
        path: "/Function",
        component: () => import('../components/FunctionView.vue'),
    },
    {
        name: 'CheckInRec',
        path: "/CheckInRec",
        component: () => import('../components/CheckInRecView.vue'),
    },
    {
        name: 'SalaryQuery',
        path: "/SalaryQuery",
        component: () => import('../components/SalaryQueryView.vue'),
    },
    {
        name: 'SalaryManage',
        path: "/SalaryManage",
        component: () => import('../components/SalaryManageView.vue'),
    },

]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes: routes,
    linkActiveClass: 'active'
})



export default router;