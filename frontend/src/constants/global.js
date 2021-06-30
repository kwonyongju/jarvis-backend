import { createGlobalStyle } from "styled-components";

import { c_black } from "../utils/colors";

export const GlobalStyle = createGlobalStyle`
  html {
    margin: 0;
  }

  body {
    font-family: Cantarell, sans-serif;
    letter-spacing: 0.5px;
    color: ${c_black};
  }

  a {
    color: black;
    text-decoration: none;
  }

  li {
    list-style-type: none;
  }
`;
