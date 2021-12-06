import React from "react";
import FoodDisplay from "./singleOrder/foodDisplay";

export default function SingleOrder() {
  return (
    <div>
      {/* back button to go back to all orders */}
      <p className="text-start mx-2 my-1">back</p>
      <h2 className="my-2">{"Order #xxx"}</h2>
      <div className="d-flex flex-column vw-50 p-3">
        {/* map each food in order */}
        <FoodDisplay />
        <FoodDisplay />
        <FoodDisplay />
      </div>
      <h3 className="text-center m-auto mb-4">{"total $xx.xx"}</h3>
     

          <button type="button" class="btn btn-secondary mb-2">load a button</button>

      {/* button for pending orders */}
      {/* <div className="btn-group">
                <button type="button" class="btn btn-sm btn-outline-danger">Reject</button>
                <button type="button" class="btn btn-sm btn-outline-success">Accept</button>
            </div> */}

      {/* button for in progress orders */}
      {/* <button type="button" class="btn btn-sm btn-outline-success">Complete</button> */}

      {/* no button for completed orders */}
    </div>
  );
}
