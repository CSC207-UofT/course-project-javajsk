import React from "react";
import PendingOrder from "./orderPreviews/pendingOrderPreview";
import InprogressOrder from "./orderPreviews/inprogressOrderPreview";
import CompletedOrder from "./orderPreviews/completedOrderPreview";

function Orders() {
  //vendor token
  //order data

  return (
    <div>
      <div className="display-5 my-2">
        {/* Add shop name here */}
        Orders for shop {}
      </div>
      <div className="d-inline-block">
        <div className="card my-4 shadow">

          <div className="card-header">
            <p className="h5 text-danger">
              Pending Orders
            </p>
          </div>
          <div className="card-body">
            <PendingOrder />
            <PendingOrder />
          </div>
        </div>

        <div className="card my-4 shadow">

          <div className="card-header">
            <p className="h5 text-warning">
              In-Progress Orders
            </p>
          </div>
          <div className="card-body">
            <InprogressOrder />
            <InprogressOrder />
          </div>
        </div>

        <div className="card my-4 shadow">

          <div className="card-header">
            <p className="h5 text-success">
              Completed Orders
            </p>
          </div>
          <div className="card-body">
            <CompletedOrder />
            <CompletedOrder />
          </div>
        </div>
      </div>

    </div>
  );
}

export default Orders;
