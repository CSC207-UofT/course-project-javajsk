import React from "react";
import PendingOrder from "./orderPreviews/pendingOrderPreview";
import InprogressOrder from "./orderPreviews/inprogressOrderPreview";
import CompletedOrder from "./orderPreviews/completedOrderPreview";

function Orders() {
  //vendor token
  //order data

  return (
    <div>
      <h1>Orders</h1>
      <div className="d-inline-block">
        <h5 className="m-auto text-muted text-start p-1">pending</h5>
        <div>
          {/* a map to all the orders with pending statuses */}
          <PendingOrder />
          <PendingOrder />
        </div>

        <h5 className="m-auto text-muted text-start p-1">in progress</h5>
        <div>
          {/* a map to all the orders with in progress statuses */}
          <InprogressOrder />
          <InprogressOrder />
          <InprogressOrder />
        </div>

        <h5 className="m-auto text-muted text-start p-1">completed</h5>
        <div>
          {/* a map to all the orders with completed statuses */}
          <CompletedOrder />
          <CompletedOrder />
          <CompletedOrder />
          <CompletedOrder />
        </div>
      </div>
    </div>
  );
}

export default Orders;
