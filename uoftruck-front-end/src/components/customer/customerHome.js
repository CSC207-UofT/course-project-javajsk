import { useEffect, useState } from "react";
import { Redirect } from "react-router";
import AuthenticateUser, { GetUserName } from "../../mechanisms/authenticateTokens";
import { CustomerSignUp, CustomerLogin } from "./customerLoginSignup";
import { Switch, Route, useRouteMatch } from "react-router";
import AllTrucksPage, { TruckPage } from "./allTrucksPage";
import { Link } from "react-router-dom";

export function CustomerHome(){
    let {path, url} = useRouteMatch();
    return(
        <div className="CustomerHome">
            <Switch>
              <Route path={`${path}/login`} exact component={CustomerLogin}/>
              <Route path={`${path}/signup`} exact component={CustomerSignUp} />
              <Route path={`${path}/truck/:id`} exact component={TruckPage} />
              <Route path={`${path}/truck/`} exact component={TruckPage}>
                  <Redirect to={path}/>
              </Route>
              <Route path={`${path}/cart/`} exact component={Cart} />
              <Route path={path} exact component={CustomerDashboard}/>
            </Switch>
            <CartIcon/>
        </div>
    );
}

function CartIcon(){
    let {path, url} = useRouteMatch();
    if(AuthenticateUser()){
    return(
    <div class="cartButton my-2 mx-2 ">
        <Link to={`${path}/cart`}>
        <button class="btn rounded-pill btn-dark fs-3">
            🛒
        </button>
        </Link>
    </div>
    );
    }
    return(
        <div></div>
    );
}

function Cart(){
    return(
        <div>
            <div class="container">
                Your cart:
            </div>
        </div>
    )
}


function CustomerDashboard(){
    const [shops, setShops] = useState(null);
    useEffect(()=>{

    },[])
    if(!AuthenticateUser()){
        return(
            <Redirect to="/customer/login"/>
        )
    }
    let username = GetUserName();
    return(
        <div class="container my-2">
            <div class="text-start">
                <p class="text-muted">
                    Hello {username}, what would you like to eat today?
                </p>
            </div>
            <AllTrucksPage/>
        </div>
    )

    
}