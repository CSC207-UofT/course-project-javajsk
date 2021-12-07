import axios from "axios";
import { domain,port  } from "../../mechanisms/contexts";
import { useState } from "react";
import { Redirect } from "react-router";
import { Link } from "react-router-dom";
import { SetUser } from "../../mechanisms/authenticateTokens";
import AuthenticateUser from "../../mechanisms/authenticateTokens";
const customerSignup = '/CustomerSignUp/'

export default function CustomerLoginSignup(){
    return(
        <CustomerSignUp/>
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
        "confirmed_password": event.target[2].value
    }   
    axios.put(`${domain}:${port}/CustomerSignUp/${formData.username}/${formData.password}/${formData.confirmed_password}`).then((output)=>{
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
    axios.put(`${domain}:${port}/CustomerLogin/${formData.username}/${formData.password}/`).then((output)=>{
        setter(output.data);
   });

}

function CustomerLogin(){
    const [request, setRequest] = useState(null);
    if(request != null){
        if(request.contents != ""){
            SetUser(request.contents);
        }
    }
    if(AuthenticateUser()){
        return(
            <Redirect to="/customer/"/>
        )
    }
    return(
        <div className="container my-3">
            <div class="h4">Customer Login</div>
            <form onSubmit={(event) => login(event, setRequest)}>
                {generateError(request,"/customer/")}
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
                <Link to="/customer/signup">New? Sign up here!</Link>
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

function CustomerSignUp(props){
    const [request, setRequest] = useState(null);
    if(AuthenticateUser()){
        return(
            <Redirect to="/customer/"/>
        )
    }
    return(
        <div className="container px-3  my-3">
            
            <form onSubmit={(event) => submitSignup(event,setRequest)}>
                {generateError(request,"/customer/login")}
                <div className="card">
                <div className="card-header">
                    Customer Information
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
                <button type="submit" className="btn btn-primary my-2 w-50">Submit </button>
            </form>
            <div className="container my-3">
                <Link to="/customer/login">Login instead</Link>
            </div>
        </div>
    )
}

export {CustomerSignUp, CustomerLogin};