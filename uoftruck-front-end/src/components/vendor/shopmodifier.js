import axios from "axios";
import { useEffect, useState } from "react";
import { Redirect } from "react-router";
import AuthenticateUser, { GetShopId } from "../../mechanisms/authenticateTokens";
import { domain, port } from "../../mechanisms/contexts";

export function ModifyShop(){
    const shopId = GetShopId();
    const [data, setData] = useState(null);
    useEffect(()=>{
        axios.get(`${domain}:${port}/GetShop/${shopId}`).then((response)=>{
            setData(response.data);
        })
    },[])
    if(!AuthenticateUser()){
        return(
            <Redirect to="/vendor/"/>
        );
    }
    
    


    if(data == null){
        return(
            <div class="spinner-border" role="status">
                        <span class="sr-only"></span>
            </div>
        )
    }
    if(data.status == 200){
        let shop = JSON.parse(data.contents);
        console.log(shop);
        return (
                <div class="my-1">
                    <div class="display-5 my-2">Shop Information</div>
                    <div class="container">
                        <div class="card">
                            <div class="card-header">
                                <div class="row">
                                    <div class="col text-start">
                                        {shop.name}
                                    </div>
                                    <div class="col text-end">
                                        <small>
                                            {shop.isOpen ? 
                                            <div class="text-succecss">Open</div> : <div class="text-danger">Closed</div>    
                                        }
                                        </small>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <div class="row">
                                    <div class="col ">
                                        Currently at: {shop.location}
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="card my-3">
                            <div class="card-header">
                                Past month metrics
                            </div>
                            <div class="card-body">
                            <div class="row">
                                    <div class="col text-start ">
                                        Total orders:
                                    </div>
                                    <div class="col text-muted">
                                        {320} <small class="text-success">▲20%</small>
                                    </div>
                                    <div class="w-100 my-2"></div>
                                    <div class="col text-start">
                                        Most popular:
                                    </div>
                                    <div class="col text-muted">
                                        {"The golden dog"}
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
        )
    }else{
        return(
            <div class="text-danger">Unable to load shop information</div>
        )
    }
    
}