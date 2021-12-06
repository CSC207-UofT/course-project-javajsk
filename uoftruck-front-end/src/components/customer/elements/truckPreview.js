import React, { Component } from "react";

class truckPreview extends React.Component {
  render() {
    // get order data
    return (
      <div className="d-flex w-80 justify-content-center p-2 border border-dark rounded my-1">
        {/* it should display truck names based on database */}
        <h4 className="m-auto mx-4">
          {"Epic Truck"}
          <small>
            <ul class="list-unstyled">
              <li>Location</li>
            </ul>
          </small>
        </h4>
        <div className="btn-group">
          {/* clicking on the buttons should update the order state, then reload the orders page */}
          <button type="button" class="btn btn-sm btn-outline-primary">
            View
          </button>
        </div>
      </div>
    );
  }
}

export default truckPreview;
