import { createGlobalStyle } from "styled-components";

const GlobalStyle = createGlobalStyle`
  * {
    font-family: "Mulish", sans-serif;
  }

  body {
    margin: 0;
    padding: 0;
    background: #051622;
    color: white;
    margin-bottom: 4rem
  }

  /* width */
  ::-webkit-scrollbar {
    position: absolute;
    width: 0.8rem;
  }
  /* Track */
  ::-webkit-scrollbar-track {
    background: #051622;
    border-inline: 1px solid #deb992;
    border-radius: 10px;
  }
  /* Handle */
  ::-webkit-scrollbar-thumb {
    background: rgba(11, 45, 69, 0.3);
    box-shadow: inset 0 0 0.35rem #deb992;
    border: 1px solid rgb(11, 45, 69);
    border-radius: 10px;
  }

  h1 {
    margin: 2rem 2rem 1rem;
    color: #1ba098;
    text-align: center;
  }
`;

export default GlobalStyle;
