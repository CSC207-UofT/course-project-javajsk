import { Redirect } from "react-router";
import AuthenticateUser, { GetShopId } from "../../mechanisms/authenticateTokens";
import { Link } from "react-router-dom";
import { Router, Route, Switch, useRouteMatch } from "react-router-dom";
import { CreateAddon, CreateFood, CreateSingleton } from "./creation";
import { useEffect, useState } from "react";
import { domain, port } from "../../mechanisms/contexts";
import axios from 'axios';



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
    let shopId = GetShopId();
    return(
        
        <div className="main-menu container my-3">
             <SelectorCreation/>
                <div class="shadow my-4">
                <Foods/>
                </div>
                <div class="shadow my-4">
                <Singletons/>
                </div>
                <div class="shadow my-4">
                <Addons/>
                </div>
            </div>
    );
}

function Foods(){
    const [data, setData] = useState(null);
    const shopId = GetShopId();
    useEffect(()=>{
        axios.get(`${domain}:${port}/GetShopFoods/${shopId}`).then((resp)=>{
            setData(resp.data)
        });
    },[]);
    if(data === null){
        return (
            <div className="card my-3">
                <div className="card-header">
                    <p className="text-muted w-100">
                        Foods
                    </p>
                    
                </div>
                <div className="card-body">
                    <div class="spinner-border" role="status">
                        <span class="sr-only">Loading...</span>
                    </div>
                </div>
            </div>
        );
    }else{
        return(
        <div className="card my-3">
            <div className="card-header">
                <p className="text-muted w-100">
                Foods
                </p>
                <div class="container-fluid">
                        <div class="row text-muted">
                            <div class="col">
                                <div  style={{width:100}}>
                                <small>Name</small>
                                </div>
                            </div>
                            <div class="col">
                                <small>Price</small>
                            </div>
                            <div class="col">
                                <small>Availablity</small>
                            </div>
                        </div>
                    </div>
            </div>
            <div className="card-body py-0 px-0">
                {data.contents.map((i)=>{
                        let dat = JSON.parse(i);
                        return(
                            <div className="border border-light w-100 listItem container-fluid">
                                <div class="container">
                                    <div class="row my-1">
                                        <div class="col">
                                            <div class="textofwr">
                                              <small>
                                            {dat.name}
                                            </small>
                                        
                                            </div>
                                        </div>
                                        <div class="col text-muted">
                                        {dat.price}$
                                        </div>
                                        <div class="col">
                                        {dat.isAvaiable ? <div class="text-success">Yes</div> : <div class="text-danger">No</div> }
                                        </div>
                                    </div>
                                </div>
                                
                            </div>
                        );
                    })}
            </div>
        </div>);
    }

}
function Singletons(){
    const [data, setData] = useState(null);
    const shopId = GetShopId();
    useEffect(()=>{
        axios.get(`${domain}:${port}/GetShopSingletons/${shopId}`).then((resp)=>{
            setData(resp.data)
        });
    },[]);
    if(data === null){
        return (
            <div className="card my-3">
                <div className="card-header">
                    <p className="text-muted w-100">
                        Singletons
                    </p>
                    
                </div>
                <div className="card-body">
                    <div class="spinner-border" role="status">
                        <span class="sr-only">Loading...</span>
                    </div>
                </div>
            </div>
        );
    }else{
        return(
        <div className="card my-3">
            <div className="card-header">
                <p className="text-muted w-100">
                Singletons
                </p>
                <div class="container-fluid">
                        <div class="row text-muted">
                            <div class="col">
                                <div  style={{width:100}}>
                                <small>Name</small>
                                </div>
                            </div>
                            <div class="col">
                                <small>Price</small>
                            </div>
                            <div class="col">
                                <small>Availablity</small>
                            </div>
                        </div>
                    </div>
            </div>
            <div className="card-body py-0 px-0">
                {data.contents.map((i)=>{
                        let dat = JSON.parse(i);
                        return(
                            <div className="border border-light w-100 listItem container-fluid">
                                <div class="container">
                                    <div class="row my-1">
                                        <div class="col">
                                            <div class="textofwr">
                                              <small>
                                            {dat.name}
                                            </small>
                                        
                                            </div>
                                        </div>
                                        <div class="col text-muted">
                                        {dat.price}$
                                        </div>
                                        <div class="col">
                                        {dat.isAvaiable ? <div class="text-success">Yes</div> : <div class="text-danger">No</div> }
                                        </div>
                                    </div>
                                </div>
                                
                            </div>
                        );
                    })}
            </div>
        </div>);
    }

}

function Addons(){
    const [data, setData] = useState(null);
    const shopId = GetShopId();
    useEffect(()=>{
        axios.get(`${domain}:${port}/GetShopAddons/${shopId}`).then((resp)=>{
            setData(resp.data);
        });
    },[]);
    if(data === null){
        return (
            <div className="card my-3">
                <div className="card-header">
                    <p className="text-muted">
                        Addons
                    </p>
                </div>
                <div className="card-body">
                    <div class="spinner-border" role="status">
                        <span class="sr-only"></span>
                    </div>
                </div>
            </div>
        );
    }else{
        if(data.status == 200){
            return(
            <div className="card my-3">
                <div className="card-header">
                    <p className="text-muted w-100">
                        Addons
                    </p>
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col">
                                <small class="text-muted">
                                    Name
                                </small>
                            </div>
                            <div class="col">
                                <small class="text-muted">
                                    Price
                                </small>
                            </div>
                            <div class="col">
                                <small class="text-muted">
                                    Availablity
                                </small>
                            </div>
                        </div>
                    </div>
                </div>
                <div className="card-body py-0 px-0">
                    {data.contents.map((i)=>{
                        let dat = JSON.parse(i);
                        return(
                            <div className="border border-light w-100 listItem container-fluid">
                                <div class="container">
                                    <div class="row my-1">
                                        <div class="col">
                                        {dat.name}
                                        </div>
                                        <div class="col text-muted">
                                        {dat.price}$
                                        </div>
                                        <div class="col">
                                        {dat.isAvaiable ? <div class="text-success">Yes</div> : <div class="text-danger">No</div> }
                                        </div>
                                    </div>
                                </div>
                                
                            </div>
                        );
                    })}
                </div>
            </div>);
        }else{
            return(
                <div className="card my-3">
                <div className="card-header">
                    <p className="text-muted">
                        Addons
                    </p>
                </div>
                <div className="card-body text-danger">
                    Unable to retrieve data.
                </div>
            </div>
            )
        }
    }

}


function SelectorCreation(){
    return(
        <div className="container my-3">
            <div className="row">
                <div className="col">
                    <Link to="createfood/" className="btn btn-primary">Create Food</Link>
                </div>  
                <div className="col">
                    <Link to="createsingleton/" className="btn btn-success">Create Singleton</Link>
                </div>
                <div className="col">
                    <Link to="createaddon/" className="btn btn-danger">Create Addon</Link>
                </div>
            </div>
        </div>
    )
}