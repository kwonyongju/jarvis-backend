import React from "react";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import "./App.css";

import Content from "./components/layout/Content";
import Header from "./components/layout/Header";
import Navbar from "./components/nav/Navbar";
import { GlobalStyle } from "./constants/global";
import Main from "./pages/Main";
import Customer from "./pages/Customer";
import Manager from "./pages/Manager";
import { NAV_ITEMS } from "./constants/navItems";
import Menu from "./pages/customer/Menu";
import PurchaseHistory from "./pages/customer/Purchases";
import Sales from "./pages/manager/Sales";
import Inventory from "./pages/manager/Inventory";
import OrderHistory from "./pages/manager/Orders";
import IngredientsList from "./pages/manager/Ingredients";

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
            <Route path="/menu">
              <Menu />
            </Route>
            <Route path="/purchases">
              <PurchaseHistory />
            </Route>
            <Route path="/manager">
              <Manager />
            </Route>
            <Route path="/sales">
              <Sales />
            </Route>
            <Route path="/inventory">
              <Inventory />
            </Route>
            <Route path="/ingredients">
              <IngredientsList />
            </Route>
            <Route path="/orders">
              <OrderHistory />
            </Route>
          </Switch>
        </Content>
      </Router>
    </>
  );
};

export default App;
