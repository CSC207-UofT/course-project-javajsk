import { Redirect } from "react-router";
import AuthenticateUser, { GetShopId, GetToken } from "../../mechanisms/authenticateTokens";
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

function SetAvailability(oldObj, type, token, items, setItems ){
    let avail = true;
    let object = JSON.parse(JSON.stringify(oldObj));
    if(object.isAvailable){
        avail = false;
    }
    object.isAvailable = avail;
    axios.put(`${domain}:${port}/Modify${type}/${token}/${object.id}`, object).then((resp)=>{
        if(resp.data.status == 200){
            setItem(items, setItems, oldObj, JSON.parse(resp.data.contents));
        }
    });

}

function AvailabilityButton(data, type, token, foodDat, setFoodDat){
    if(data.isAvailable== true  ){
        return (<button class="btn btn-success" onClick={()=>{SetAvailability(data, type, token, foodDat, setFoodDat)}}>Yes</button>
        );
    }
    return (<button class="btn btn-danger" onClick={()=>{SetAvailability(data, type, token, foodDat, setFoodDat)}}>No</button>
    );
}

function parseArr(arr){
    let newArr = [];
    arr.map((elem)=>{
        newArr.push(JSON.parse(elem));
    })
    return newArr;
}

function setItem(data, setDat, oldElem, newElem){
    let newArr = data.contents;

    let index = newArr.indexOf(oldElem);
    if(index !== -1){
        newArr[index] = newElem;
    }
    setDat({...data, contents:newArr});
}   

function IsFoodAvailable(data,singletons){
    if(singletons == null){
        return(<div class="spinner-border" role="status">
            <span class="sr-only"></span>
        </div>);
    }
    let available = true;
    data.components.map((elem)=>{
        singletons.map((raw) =>{
            let single = JSON.parse(raw)
            if(single.id == elem){
                if(!single.isAvailable){
                    available = false;
                }
            }
        });
    })
    
    if(available){
    return(
        <div class="text-success">Yes</div>
    );
    }
    return(
        <div class="text-danger">No</div>
    )
}

function Foods(){
    const [data, setData] = useState(null);
    const [singletons, setSingletons] = useState(null);
    const shopId = GetShopId();
    
    useEffect(()=>{
        setInterval(()=>{
            axios.get(`${domain}:${port}/GetShopFoods/${shopId}`).then((resp)=>{
                resp.data.contents = parseArr(resp.data.contents);
                setData(resp.data);
            });
    
            axios.get(`${domain}:${port}/GetShopSingletons/${shopId}`).then((resp)=>{
                if(resp.data.status == 200){
                    setSingletons(resp.data.contents);
                }
            });

        },
        1000);
        
        
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
                        <span class="sr-only"></span>
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
                {data.contents.map((dat)=>{
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
                                        {IsFoodAvailable(dat,singletons)
                                    }
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
    const token = GetToken();
    useEffect(()=>{
        axios.get(`${domain}:${port}/GetShopSingletons/${shopId}`).then((resp)=>{
            if(resp.data.status === 200){
                resp.data.contents = parseArr(resp.data.contents);
                setData(resp.data)
            }
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
                        <span class="sr-only"></span>
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
                {data.contents.map((dat)=>{
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
                                        {AvailabilityButton(dat, "Singleton", token, data, setData) }
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
    const token = GetToken();
    useEffect(()=>{
        axios.get(`${domain}:${port}/GetShopAddons/${shopId}`).then((resp)=>{
            
            if(resp.data.status === 200){
                resp.data.contents = parseArr(resp.data.contents);
                setData(resp.data)
            }
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
                    {data.contents.map((dat)=>{
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
                                        {AvailabilityButton(dat, "Addon", token, data, setData) }
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
    let {path, url} = useRouteMatch();
    return(
        <div className="container my-3">
            <div className="row">
                <div className="col">
                    <Link to={`${path}createfood/`} className="btn btn-primary">Create Food</Link>
                </div>  
                <div className="col">
                    <Link to={`${path}createsingleton/`} className="btn btn-success">Create Singleton</Link>
                </div>
                <div className="col">
                    <Link to={`${path}createaddon/`} className="btn btn-danger">Create Addon</Link>
                </div>
            </div>
        </div>
    )
}