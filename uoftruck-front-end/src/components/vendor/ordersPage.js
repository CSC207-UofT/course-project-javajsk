import React, { useEffect, useState } from "react";
import PendingOrder from "./orderPreviews/pendingOrderPreview";
import InprogressOrder from "./orderPreviews/inprogressOrderPreview";
import CompletedOrder from "./orderPreviews/completedOrderPreview";
import axios from "axios";
import { GetShopId } from "../../mechanisms/authenticateTokens";
import {domain, port} from "../../mechanisms/contexts";

function Orders() {
  //vendor token
  //order data
  const [shopDat, setShopDat] = useState(null);
  const shopId = GetShopId();
  useEffect(()=>{
    axios.get(`${domain}:${port}/GetShop/${shopId}`).then((response)=>{
      setShopDat(response.data);
  })
  },[])
  if(shopDat == null){
    return(
      <div class="container">
          <div class="spinner-border" role="status">
              <span class="sr-only"></span>
          </div>
      </div>
    )
  }

  let shop = JSON.parse(shopDat.contents);
  return (
    <div>
      <div className="display-5 my-2">
        {/* Add shop name here */}
        Orders for <div class="lead">{shop.name}</div>
      </div>
      <div className="d-inline-block">
        <div className="card my-4 shadow">

          <div className="card-header">
            <p className="h5 text-danger">
              Pending Orders
            </p>
          </div>
          <div className="card-body">
            <PendingOrder orderId="9AF"/>
            <PendingOrder orderId="80E"/>
          </div>
        </div>

        <div className="card my-4 shadow">

          <div className="card-header">
            <p className="h5 text-warning">
              In-Progress Orders
            </p>
          </div>
          <div className="card-body">
            <InprogressOrder orderId="C10" />
            <InprogressOrder orderId="B20" />
          </div>
        </div>

        <div className="card my-4 shadow">

          <div className="card-header">
            <p className="h5 text-success">
              Completed Orders
            </p>
          </div>
          <div className="card-body">
            <CompletedOrder orderId="F2A"/>
            <CompletedOrder orderId="S3Y"/>
          </div>
        </div>
      </div>

    </div>
  );
}

export default Orders;
