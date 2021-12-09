import React from "react";
import PastOrderPreview from "./elements/pastOrderPreview";

function orderHistoryPage() {
  //vendor token
  //order data

  return (
    <div>
      <h1>Past Orders</h1>
      <div className="d-inline-block">
        <h5 className="m-auto text-muted text-start p-1">November 2021</h5>
        <div>
          {/* a map to all the pastOrderPreviews in this section */}
          <PastOrderPreview />
          <PastOrderPreview />
          <PastOrderPreview />
          <PastOrderPreview />
          <PastOrderPreview />
          <PastOrderPreview />
          <PastOrderPreview />
        </div>
      </div>
    </div>
  );
}

export default orderHistoryPage;
