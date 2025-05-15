// main.tsx 또는 index.tsx
import React from "react";
import ReactDOM from "react-dom/client";
import App from "./App";
import "./index.css";

setInterval(() => {
  window.location.reload();
}, 30000);

ReactDOM.createRoot(document.getElementById("root")!).render(
  <React.StrictMode>
    <div className="w-screen h-screen flex items-center justify-center overflow-hidden">
      <div className="scale-[0.95]">
        <App />
      </div>
    </div>
  </React.StrictMode>
);
