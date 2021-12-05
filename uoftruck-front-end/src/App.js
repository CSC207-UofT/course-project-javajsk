import "./App.css";
import { BrowserRouter, Switch, Route, Link } from "react-router-dom";
import { UserContext } from "./mechanisms/contexts";
import { useState } from "react";
import Customer from "./components/customer/customer";
import allTrucksPage from "./components/customer/allTrucksPage";
import ordersPage from "./components/vendor/ordersPage";

function App() {
  const [userContext, setUserContext] = useState(null);
  return (
    <UserContext.Provider value={[userContext, setUserContext]}>
      <div className="App">
        <link
          rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous"
        />
        <div className="navbar navbar-dark bg-light border ">
          <div className="container">
            <div className="mx-auto">
              <h3 className="display-5 font-weight-light">UofTruck</h3>
            </div>
          </div>
        </div>
        <BrowserRouter>
          <Switch>
            <Route path="/vendor" exact component={test} />
            <Route path="/customer/" exact component={Customer} />
            <Route path="/" exact component={chooser} />
            <Route path="/test" exact component={allTrucksPage} />
            <Route path="/test1" exact component={ordersPage} />
          </Switch>
        </BrowserRouter>
      </div>
    </UserContext.Provider>
  );
}

function chooser() {
  return (
    <div className="container py-5">
      <div className="message">
        <p className="text-left">
          Welcome! This is UofTruck please choose an option!
        </p>
      </div>
      <div className="container-fluid">
        <Link to="/customer">
          <button className="btn btn-primary w-100">Enter as Customer</button>
        </Link>
      </div>
      <div className="container-fluid my-5">
        <Link to="/vendor">
          <button className="btn btn-secondary w-100">Enter as Vendor</button>
        </Link>
      </div>
    </div>
  );
}

function test() {
  return (
    <div>
      <h1> Hello</h1>
    </div>
  );
}

function userEnd() {}

export default App;
