import React from "react";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";

import Content from "./components/layout/Content";
import { GlobalStyle } from "./constants/global";
import MainPage from "./components/MainPage";

const App = () => {
  return (
    <>
      <Router>
        <GlobalStyle />
        <Route exact path="/">
          {/* <Home /> */}
        </Route>
        <Content>
          <MainPage />
          <Switch>
            <Route path="/customer">{/* <Projects /> */}</Route>
            <Route path="/manager">{/* <Resume /> */}</Route>
          </Switch>
        </Content>
      </Router>
    </>
  );
};

export default App;
