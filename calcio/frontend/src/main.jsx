import React from "react";
import { createRoot } from "react-dom/client";
import { App } from "./App.jsx";

const rootElement = document.getElementById("react-referees-root");
const rawData = window.SIW_REFEREES_DATA || [];

if (rootElement) {
  createRoot(rootElement).render(<App referees={rawData} />);
}
