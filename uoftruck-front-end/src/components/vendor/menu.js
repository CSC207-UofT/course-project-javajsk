import { Redirect } from "react-router";
import AuthenticateUser from "../../mechanisms/authenticateTokens";
import { Link } from "react-router-dom";
import { Router, Route, Switch, useRouteMatch } from "react-router-dom";
import { CreateAddon, CreateFood, CreateSingleton } from "./creation";
import NestedBrowserRouter from 'nested-browser-router';

export function Menu(props){
    let {path, url } = useRouteMatch();
    if(!AuthenticateUser()){
        return(
            <Redirect to="/vendor/"/>
        )
    }else{
        return(
            <div className="menu my-4">
               
                    <Switch>
                        <Route path={`${path}/createfood`} component={CreateFood}/>
                        <Route path={`${path}/createsingleton`} component={CreateSingleton} />
                        <Route path={`${path}/createaddon`} component={CreateAddon} />
                        <Route path={`${path}/`} exact component={MainMenu} />
                    </Switch>
            
            </div>
        )
    }

}

function MainMenu(){
    return(
        
        <div className="main-menu container my-3">
             <SelectorCreation/>
                <div className="card">
                    <div className="card-header">
                        <p className="text-muted">
                            Foods
                        </p>
                    </div>
                    <div className="card-body">
                        
                    </div>
                </div>

                <div className="card my-3">
                    <div className="card-header">
                        <p className="text-muted">
                            Addons
                        </p>
                    </div>
                    <div className="card-body">
                        
                    </div>
                </div>
            </div>
    );
}

function SelectorCreation(){
    return(
        <div className="container my-3">
            <div className="row">
                <div className="col">
                    <Link to="createfood/" className="btn btn-primary">Create Food</Link>
                </div>  
                <div className="col">
                    <Link to="createsingleton/" className="btn btn-secondary">Create Singleton</Link>
                </div>
                <div className="col">
                    <Link to="createaddon/" className="btn btn-dark">Create Addon</Link>
                </div>
            </div>
        </div>
    )
}