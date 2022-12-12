// vite.config.js
import { defineConfig } from "file:///D:/projects/IDEAProjects/kingOfBots/web/node_modules/.pnpm/registry.npmmirror.com+vite@3.1.8_sass@1.55.0/node_modules/vite/dist/node/index.js";
import vue from "file:///D:/projects/IDEAProjects/kingOfBots/web/node_modules/.pnpm/registry.npmmirror.com+@vitejs+plugin-vue@3.1.2_vite@3.1.8+vue@3.2.41/node_modules/@vitejs/plugin-vue/dist/index.mjs";
import Components from "file:///D:/projects/IDEAProjects/kingOfBots/web/node_modules/.pnpm/registry.npmmirror.com+unplugin-vue-components@0.22.9_vue@3.2.41/node_modules/unplugin-vue-components/dist/vite.mjs";
import { AntDesignVueResolver } from "file:///D:/projects/IDEAProjects/kingOfBots/web/node_modules/.pnpm/registry.npmmirror.com+unplugin-vue-components@0.22.9_vue@3.2.41/node_modules/unplugin-vue-components/dist/resolvers.mjs";
var vite_config_default = defineConfig({
  plugins: [
    vue(),
    Components({
      resolvers: [
        AntDesignVueResolver({
          importStyle: "scss"
        })
      ]
    })
  ],
  resolve: {
    alias: {
      "@": "/src"
    },
    extensions: [".js", ".vue", ".json"]
  }
});
export {
  vite_config_default as default
};
//# sourceMappingURL=data:application/json;base64,ewogICJ2ZXJzaW9uIjogMywKICAic291cmNlcyI6IFsidml0ZS5jb25maWcuanMiXSwKICAic291cmNlc0NvbnRlbnQiOiBbImNvbnN0IF9fdml0ZV9pbmplY3RlZF9vcmlnaW5hbF9kaXJuYW1lID0gXCJEOlxcXFxwcm9qZWN0c1xcXFxJREVBUHJvamVjdHNcXFxca2luZ09mQm90c1xcXFx3ZWJcIjtjb25zdCBfX3ZpdGVfaW5qZWN0ZWRfb3JpZ2luYWxfZmlsZW5hbWUgPSBcIkQ6XFxcXHByb2plY3RzXFxcXElERUFQcm9qZWN0c1xcXFxraW5nT2ZCb3RzXFxcXHdlYlxcXFx2aXRlLmNvbmZpZy5qc1wiO2NvbnN0IF9fdml0ZV9pbmplY3RlZF9vcmlnaW5hbF9pbXBvcnRfbWV0YV91cmwgPSBcImZpbGU6Ly8vRDovcHJvamVjdHMvSURFQVByb2plY3RzL2tpbmdPZkJvdHMvd2ViL3ZpdGUuY29uZmlnLmpzXCI7aW1wb3J0IHsgZGVmaW5lQ29uZmlnIH0gZnJvbSBcInZpdGVcIjtcbmltcG9ydCB2dWUgZnJvbSBcIkB2aXRlanMvcGx1Z2luLXZ1ZVwiO1xuaW1wb3J0IENvbXBvbmVudHMgZnJvbSBcInVucGx1Z2luLXZ1ZS1jb21wb25lbnRzL3ZpdGVcIjtcbmltcG9ydCB7IEFudERlc2lnblZ1ZVJlc29sdmVyIH0gZnJvbSBcInVucGx1Z2luLXZ1ZS1jb21wb25lbnRzL3Jlc29sdmVyc1wiO1xuXG5leHBvcnQgZGVmYXVsdCBkZWZpbmVDb25maWcoe1xuICBwbHVnaW5zOiBbXG4gICAgdnVlKCksXG4gICAgQ29tcG9uZW50cyh7XG4gICAgICByZXNvbHZlcnM6IFtcbiAgICAgICAgQW50RGVzaWduVnVlUmVzb2x2ZXIoe1xuICAgICAgICAgIGltcG9ydFN0eWxlOiBcInNjc3NcIiwgLy8gXHU0RTAwXHU1QjlBXHU4OTgxXHU1RjAwXHU1NDJGXHU4RkQ5XHU0RTJBXHU5MTREXHU3RjZFXHU5ODc5XG4gICAgICAgIH0pLFxuICAgICAgXSxcbiAgICB9KSxcbiAgXSxcbiAgcmVzb2x2ZToge1xuICAgIGFsaWFzOiB7XG4gICAgICBcIkBcIjogXCIvc3JjXCIsXG4gICAgfSxcbiAgICBleHRlbnNpb25zOiBbXCIuanNcIiwgXCIudnVlXCIsIFwiLmpzb25cIl0sXG4gIH0sXG59KTtcbiJdLAogICJtYXBwaW5ncyI6ICI7QUFBaVQsU0FBUyxvQkFBb0I7QUFDOVUsT0FBTyxTQUFTO0FBQ2hCLE9BQU8sZ0JBQWdCO0FBQ3ZCLFNBQVMsNEJBQTRCO0FBRXJDLElBQU8sc0JBQVEsYUFBYTtBQUFBLEVBQzFCLFNBQVM7QUFBQSxJQUNQLElBQUk7QUFBQSxJQUNKLFdBQVc7QUFBQSxNQUNULFdBQVc7QUFBQSxRQUNULHFCQUFxQjtBQUFBLFVBQ25CLGFBQWE7QUFBQSxRQUNmLENBQUM7QUFBQSxNQUNIO0FBQUEsSUFDRixDQUFDO0FBQUEsRUFDSDtBQUFBLEVBQ0EsU0FBUztBQUFBLElBQ1AsT0FBTztBQUFBLE1BQ0wsS0FBSztBQUFBLElBQ1A7QUFBQSxJQUNBLFlBQVksQ0FBQyxPQUFPLFFBQVEsT0FBTztBQUFBLEVBQ3JDO0FBQ0YsQ0FBQzsiLAogICJuYW1lcyI6IFtdCn0K
