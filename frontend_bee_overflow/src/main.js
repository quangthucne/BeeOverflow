import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.min.js'
import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import 'datatables.net-dt/css/dataTables.dataTables.min.css'
import 'bootstrap-icons/font/bootstrap-icons.css'
import './assets/form-style.css'
import './assets/rank.css'

const app = createApp(App)

app.use(router)

app.mount('#app')
