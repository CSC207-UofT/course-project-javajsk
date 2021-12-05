import React, { Component } from "react";

export default class AddonDisplay extends Component {
  render() {
    return (
      <div className="w-75 m-auto">
        <div className="d-flex flex-row justify-content-between">
          <h6>{"addon name"}</h6>
          <h6>{"qty"}</h6>
          <h6 className="text-end">{"$xx.xx"}</h6>
        </div>
      </div>
    );
  }
}
