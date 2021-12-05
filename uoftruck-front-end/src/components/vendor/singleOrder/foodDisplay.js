import React, { Component } from "react";
import SingletonDisplay from "./singletonDisplay";

export default class FoodDisplay extends Component {
  render() {
    return (
      <div className="w-75 m-auto">
        <h5 className="text-start">{"Food item"}</h5>
        <div className="d-flex flex-column">
          {/* map each singleon and selection */}
          <SingletonDisplay />
          <SingletonDisplay />
        </div>
      </div>
    );
  }
}
