import { defineConfig } from "vite";
import react from "@vitejs/plugin-react";

export default defineConfig({
  plugins: [react()],
  build: {
    outDir: "../src/main/resources/static/js/react",
    emptyOutDir: true,
    target: "es2015",
    rollupOptions: {
      input: "src/main.jsx",
      output: {
        entryFileNames: "react-referees.js",
        assetFileNames: "react-referees.[ext]",
        chunkFileNames: "react-referees.js"
      }
    }
  }
});
