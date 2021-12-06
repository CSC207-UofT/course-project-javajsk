import axios from "axios";
import { domain,port  } from "../../mechanisms/contexts";
import { useState } from "react";
import { Redirect } from "react-router";
import { Link } from "react-router-dom";
import { SetUser } from "../../mechanisms/authenticateTokens";
import AuthenticateUser from "../../mechanisms/authenticateTokens";
const vendorSignup = '/VendorSignUp/'

export default function VendorLoginSignup(){
    return(
        <VendorSignUp/>
    );
}



function submitSignup(event, setter){
    setter(null);
    event.preventDefault();
    if(!checker(event,5)){
        setter({status:403, message:"No fields can be blank."});
    }
    const formData = {
        "username": event.target[0].value,
        "password": event.target[1].value,
        "confirmed_password": event.target[2].value,
        "shop_name": event.target[3].value,
        "location": event.target[4].value
    }   
    axios.put(`${domain}:${port}/VendorSignUp/${formData.username}/${formData.password}/${formData.confirmed_password}/${formData.shop_name}/${formData.location}`).then((output)=>{
         setter(output.data);
    });
}


function login(event, setter){
    setter(null);
    event.preventDefault();
    if(!checker(event,2)){
        setter({status:403, message:"No fields can be blank."});
    }
    const formData = {
        "username": event.target[0].value,
        "password": event.target[1].value
    }   
    axios.put(`${domain}:${port}/VendorLogin/${formData.username}/${formData.password}/`).then((output)=>{
        console.log(output.data);
        setter(output.data);
   });

}

function VendorLogin(){
    const [request, setRequest] = useState(null);
    if(request != null){
        if(request.contents != ""){
            console.log("Setting token");
            SetUser(request.contents);
        }
    }
    if(AuthenticateUser()){
        return(
            <Redirect to="/vendor/"/>
        )
    }
    return(
        <div className="container my-3">
            <form onSubmit={(event) => login(event, setRequest)}>
                {generateError(request,"/vendor/")}
                <div className="form-group">
                    <label for="username">Username:</label>
                    <input type="text" className="form-control my-2" id="username"/>
                </div>
                <div className="my-2"></div>
                <div className="form-group">
                    <label for="password">Password:</label>
                    <input type="password" className="form-control my-2" id="password"/>
                </div>
                <button type="submit" className="btn btn-primary w-50">Submit </button>
            </form>
            <div className="container my-3">
                <Link to="/vendor/signup">Signup instead</Link>
            </div>
        </div>
    );
}

function generateError(data, redirect){
    if(data != null){
        if(data.status != 200){
        return(<div className="container my-2">
                    <p className="text-danger">{data.message}</p>
        </div>);
        }else{
            return(
                <Redirect to={redirect}/>
            )
        }
        
    }
    
}


function checker(data,x){
    for(var i =0; i<x; i++){
        if(data.target[i].value == ""){
            return false;
        }
    }
    return true;
}

function VendorSignUp(props){
    const [request, setRequest] = useState(null);
    if(AuthenticateUser()){
        return(
            <Redirect to="/vendor/"/>
        )
    }
    return(
        <div className="container px-3  my-3">
            
            <form onSubmit={(event) => submitSignup(event,setRequest)}>
                {generateError(request,"/vendor/login")}
                <div className="card">
                <div className="card-header">
                    Vendor Information
                </div>
                <div className="py-3 px-3">
                <div className="form-group">
                    <label for="username">Username:</label>
                    <input type="text" className="form-control my-2" id="username"/>
                </div>
                <div className="my-2"></div>
                <div className="form-group">
                    <label for="password">Password:</label>
                    <input type="password" className="form-control my-2" id="password"/>
                </div>
                <div className="form-group">
                    <label for="passwordconf">Password Confirmation:</label>
                    <input type="password" className="form-control my-2" id="passwordconf"/>
                </div>
                </div>
                </div>

                <div className="card my-4">
                    <div className="card-header mb-2">
                        Shop Information
                    </div>
                    <div className="container py-2">
                    <div className="form-group">
                        <label for="shopname">Shop (FoodTruck) Name:</label>
                        <input type="text" className="form-control my-2" id="shopname"/>
                    </div>

                    <div>
                        <label for="shoploc">Shop Location: </label>
                        <input type="text" className="form-control my-2" id="shoploc"/>
                    </div>
                    </div>
                </div>
                <button type="submit" className="btn btn-primary w-50">Submit </button>
            </form>
            <div className="container my-3">
                <Link to="/vendor/login">Login instead</Link>
            </div>
        </div>
    )
}

export {VendorSignUp, VendorLogin};