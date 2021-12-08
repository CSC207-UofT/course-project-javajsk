import React, { useEffect, useState } from "react";
import TruckPreview from "./elements/truckPreview";
import axios from "axios";
import { domain,port } from "../../mechanisms/contexts";
import { GetShopId } from "../../mechanisms/authenticateTokens";

function AllTrucksPage() {
  //vendor token
  //order data

  return (
    <div>
      <div class="card">
        <div class="card-header text-start">
          Shops
        </div>
        <TruckData/>
      </div>
    </div>
  );
}


function TruckData(){
  const [shops, setShops]  = useState(null);
  useEffect(()=>{
    axios.get(`${domain}:${port}/GetShopFoods/`).then((resp)=>{
      setShops(resp.data)
  });
  },[])
  if(shops == null){
    return(
      <div class="spinner-border my-2 mx-2" role="status">
        <span class="sr-only"></span>
      </div>
    );
  }

}


export function TruckPage(props){
  const shopId = props.match.params.id;
  const [shopInf, setShop] = useState(null);
  useEffect(()=>{
    axios.get(`${domain}:${port}/GetShop/${shopId}`).then((resp)=>{
      setShop(resp.data)
  });
  },[]);

  if(shopInf == null){
    return(<div class="container">
      <div class="spinner-border" role="status">
        <span class="sr-only"></span>
      </div>
    </div>);
  }

  if(shopInf.status == 403){
    return(
      <div class="container">
      <div class="display-3 text-danger"> <strong>404</strong><div class="w-100"></div> No such shop found :(</div>
        </div>
    )
  }
  const shop = JSON.parse(shopInf.contents);
  return (
    <div class="container my-2">
      <strong>{shop.name}</strong>
      <div class="w-100 px-3 row">
        <div class="col text-start">
          <div class="row">
            <div class="col">Status:</div>
            {shop.isOpen? 
          <div class="text-success col">Open</div> : <div class="text-danger col">Closed</div> }
          </div>
          
        </div>
        <div class="col text-end">
            Location: {shop.location}
        </div>
      </div>
      <div class="card my-3">
        <div class="card-header">
          Available Foods
        </div>
        <div class="card-body">
          Empty menu.
        </div>
      </div>
      <div class="card my-3">
        <div class="card-header">
          Available Addons
        </div>
        <div class="card-body">
          Empty menu.
        </div>
      </div>
    </div>
  )

}

export default AllTrucksPage;
