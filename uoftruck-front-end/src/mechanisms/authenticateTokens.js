import { useContext, useEffect } from "react";
import { UserContext } from "./contexts";
import jwt from 'jwt-decode';

export default function AuthenticateUser(){
    const [user, setUser] = useContext(UserContext);
    if(user != null){
        //return (jwt(user));
        console.log("logged in");
        return true;
    }else{
        console.log("Not logged in.");
        return false;
    }
}

export function SetUser(token){
    const [user, setUser] = useContext(UserContext);
    if(token != null){
        setUser(token);
        console.log(user);
    }
}

export function GetToken(){
    const [user, setUser] = useContext(UserContext);
    return user;
}

export function GetUserName(){
    const [user, setUser] = useContext(UserContext);
    if(user != null){
        console.log(jwt(user).sub.split(','));
        return jwt(user).sub.split(',')[1];
    }
    return "NULL";
}