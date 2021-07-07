import React from "react";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";

import Content from "./components/layout/Content";
import Header from "./components/layout/Header";
import Navbar from "./components/nav/Navbar";
import { GlobalStyle } from "./constants/global";
import Main from "./pages/Main";
import Customer from "./pages/Customer";
import Manager from "./pages/Manager";
import { NAV_ITEMS } from "./constants/navItems";

const App = () => {
  return (
    <>
      <Router>
        <GlobalStyle />
        <Header>
          <Navbar navLeft={"Home"} navItems={NAV_ITEMS} />
        </Header>
        <Content>
          <Switch>
            <Route exact path="/">
              <Main />
            </Route>
            <Route path="/customer">
              <Customer />
            </Route>
            <Route path="/manager">
              <Manager />
            </Route>
          </Switch>
        </Content>
      </Router>
    </>
  );
};

export default App;
