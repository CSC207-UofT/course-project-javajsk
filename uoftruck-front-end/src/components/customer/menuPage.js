import React from "react";
import MenuItem from "./elements/menuItem";

function menuPage() {
  //vendor token
  //order data

  return (
    <div>
      <h1>Menu</h1>
      <div className="d-inline-block">
        <h5 className="m-auto text-muted text-start p-1">Recommended</h5>
        <div>
          {/* a map to all the items in this section */}
          <MenuItem />
          <MenuItem />
        </div>

        <h5 className="m-auto text-muted text-start p-1">Full Menu</h5>
        <div>
          {/* a map to all the items in this section */}
          <MenuItem />
          <MenuItem />
          <MenuItem />
          <MenuItem />
          <MenuItem />
          <MenuItem />
          <MenuItem />
        </div>
      </div>
    </div>
  );
}

export default menuPage;
