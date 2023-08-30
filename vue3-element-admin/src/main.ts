import { createApp } from "vue";
import App from "./App.vue";
import router from "@/router";
import { setupStore } from "@/store";
import { setupDirective } from "@/directive";

import "@/permission";

// 本地SVG图标
import "virtual:svg-icons-register";
import Tmap from "@map-component/vue-tmap";
// 国际化
import i18n from "@/lang/index";

// 样式
import "element-plus/theme-chalk/dark/css-vars.css";
import "@/styles/index.scss";
import "@/styles/v-shake.scss";
import "uno.css";

const app = createApp(App);
// 全局注册 自定义指令(directive)
setupDirective(app);
// 全局注册 状态管理(store)
setupStore(app);

app.use(router).use(Tmap).use(i18n).mount("#app");

app.directive("shake", (el, vnode, preVnode) => {
  el.addEventListener("click", () => {
    el.style.animation = "shake 0.51s cubic-bezier(.36,.07,.19,.97) both";
    el.addEventListener("animationend", () => {
      el.style.animation = "";
    });
  });
});
