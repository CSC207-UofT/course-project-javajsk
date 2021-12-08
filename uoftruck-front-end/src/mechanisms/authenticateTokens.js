import { useContext, useEffect } from "react";
import { UserContext } from "./contexts";
import jwt from 'jwt-decode';

export default function AuthenticateUser(){
    const [user, setUser] = useContext(UserContext);
    if(user != null){
        //return (jwt(user));
        return true;
    }else{

        return false;
    }
}

export function GetShopId(){
    const [user, setUser] = useContext(UserContext);
    if(user != null){
        return jwt(user).sub.split(',')[2];
    }
    return "NULL";
}

export function SetUser(token){
    const [user, setUser] = useContext(UserContext);
    if(token != null){
        setUser(token);
    }
}

export function GetToken(){
    const [user, setUser] = useContext(UserContext);
    return user;
}

export function GetUserName(){
    const [user, setUser] = useContext(UserContext);
    if(user != null){
        return jwt(user).sub.split(',')[1];
    }
    return "NULL";
}