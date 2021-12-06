import React, { Component } from "react";

class pastOrderPreview extends React.Component {
  render() {
    // get order data
    return (
      <div className="d-flex w-80 justify-content-center p-2 border border-dark rounded my-1">
        {/* it should display orders based on data */}
        <h4 className="m-auto mx-4">
          {"Order from Truck X"}
          <small>
            <ul class="list-unstyled">
              <li>2021-12-25</li>
            </ul>
          </small>
        </h4>
        <div className="btn-group">
          {/* clicking on the buttons should show a detailed receipt */}
          <button type="button" class="btn btn-sm btn-outline-primary">
            View
          </button>
        </div>
      </div>
    );
  }
}

export default pastOrderPreview;
