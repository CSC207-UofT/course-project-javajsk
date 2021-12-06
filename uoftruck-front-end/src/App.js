import './App.css';
import { HashRouter, Switch, Route,Link } from 'react-router-dom'
import { UserContext } from './mechanisms/contexts';
import { useContext, useState } from 'react';
import Customer from './components/customer/customer';
import VendorHome from './components/vendor/vendorHome';
import AuthenticateUser from './mechanisms/authenticateTokens';
import { GetUserName } from './mechanisms/authenticateTokens';
import { VendorLogin } from './components/vendor/vendorLoginSignup';

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
      <NavBar/>
      <HashRouter>
      <Switch>
        <Route path="/vendor" component={VendorHome} />
        <Route path="/customer" component={Customer} />
        <Route path="/" exact component={chooser}/>
      </Switch>
      </HashRouter>
    </div>
    </UserContext.Provider>
  );
}

function Logout(){
  const [user, setUser] = useContext(UserContext);
  return(
      <div>
          <button className="btn btn-secondary" onClick={()=> {setUser(null)}}>Logout</button>
      </div>
  )
}

function NavBar(){
  const [user, setuser] = useContext(UserContext);
  const userName = GetUserName();
  if(!AuthenticateUser()){
    return(
      <div className="navbar navbar-dark bg-light border ">
      <div className="container">
        <div className="mx-auto">
            <h3 className="display-5 font-weight-light">
              UofTruck
            </h3>
          </div>
      </div>
    </div>

    );
  }else{
    return(
      <div className="navbar navbar-dark bg-light border ">
      <div className="container-fluid">
                <p className="font-weight-light text-center w-75 my-auto py-1">
                  Welcome, {userName}
                </p>
              <Logout/>
      </div>
    </div>
    );
  }
}

function chooser(){
  return(
    <div className="container py-5">
      <div className="message">
        <p className="text-left">Welcome! This is UofTruck please choose an option!</p>
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
  )
}

function test(){
  return(
    <div>
      <h1> Hello</h1>
    </div>
  )
}

function userEnd(){
  
}

export default App;
