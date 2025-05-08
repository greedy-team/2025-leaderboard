/** @type {import('tailwindcss').Config} */
export default {
  content: ["./src/**/*.{js,jsx,ts,tsx}"],
  theme: {
    extend: {
      // Figma 시안에 맞는 글꼴 지정(예시)
      fontFamily: {
        sans: ["'Pretendard Variable'", "ui-sans-serif", "system-ui"],
      },
    },
  },
  plugins: [],
  // 👉  preflight 건드리지 않음
};
