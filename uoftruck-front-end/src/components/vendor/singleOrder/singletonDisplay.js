import React, { Component } from "react";
import AddonDisplay from "./addonDisplay";

export default class SingletonDisplay extends Component {
  render() {
    return (
      <div>
        <div className="d-flex flex-row justify-content-between">
          <h6>{"singleton name"}</h6>
          <h6>{"qty"}</h6>
          <h6 className="text-end">{"$xx.xx"}</h6>
        </div>
        <div className="d-flex flex-column">
          {/* mapp the add ones to the selection */}
          <AddonDisplay />
          <AddonDisplay />
          <AddonDisplay />
        </div>
      </div>
    );
  }
}
