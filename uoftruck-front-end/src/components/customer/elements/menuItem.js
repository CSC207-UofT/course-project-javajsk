import React, { Component } from "react";

class menuItem extends React.Component {
  render() {
    // get order data
    return (
      <div className="d-flex w-80 justify-content-center p-2 border border-dark rounded my-1">
        {/* it should display menu item name based on the database */}
        <h4 className="m-auto mx-4">{"menu item abc"}</h4>
        <div className="btn-group">
          {/* clicking on the buttons should update the order state, then reload the orders page */}
          <button type="button" class="btn btn-sm btn-outline-danger">
            Remove
          </button>
          <button type="button" class="btn btn-sm btn-outline-success">
            Add
          </button>
        </div>
      </div>
    );
  }
}

export default menuItem;
