import React, { Component } from 'react';

class PendingOrder extends React.Component {
    render() { 
        // get order data
        return <div className="d-flex w-80 justify-content-center p-2 border border-dark rounded my-1">
            {/* the header links to the order page. pass order id through link as a parameter */}
            {/* it should display the order id based on the data */}
            <h4 className="m-auto mx-4">{"order #xxx"}</h4>
            <div className="btn-group">
                {/* clicking on the buttons should update the order state, then reload the orders page */}
                <button type="button" class="btn btn-sm btn-outline-danger">Reject</button>
                <button type="button" class="btn btn-sm btn-outline-success">Accept</button>
            </div>
            
        </div>;
    } 
}
 
export default PendingOrder;