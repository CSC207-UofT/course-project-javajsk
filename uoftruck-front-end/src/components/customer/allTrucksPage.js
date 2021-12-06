import React from "react";
import TruckPreview from "./elements/truckPreview";

function allTrucksPage() {
  //vendor token
  //order data

  return (
    <div>
      <h1>Trucks</h1>
      <div className="d-inline-block">
        <h5 className="m-auto text-muted text-center text-start p-1">
          Trucks Near You
        </h5>
        <div>
          {/* a map to all the items in this section */}
          <TruckPreview />
          <TruckPreview />
          <TruckPreview />
          <TruckPreview />
          <TruckPreview />
          <TruckPreview />
        </div>
      </div>
    </div>
  );
}

export default allTrucksPage;
