import App from '@/App.vue'
import { createApp } from "vue";
import  createRouter  from './router'
import VueLogger from 'vuejs3-logger';

const app = createApp(App)

app.use(createRouter)

const options = {
  isEnabled: true,
  logLevel : 'debug',
  stringifyArguments : false,
  showLogLevel : true,
  showMethodName : false,
  separator: '|',
  showConsoleColors: true
};

app.use(VueLogger, options);

app.mount('#app')





