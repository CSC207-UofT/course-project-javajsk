function CompletedOrder(props){
        // get order data
        return( 
        <div className="d-flex justify-content-center p-2 border border-dark rounded my-1">
            <h4 className="m-auto mx-4">{props.orderId}</h4>
        </div>);
    
}

export default CompletedOrder;