import React from 'react';
import {Link} from 'react-router-dom';

const AccommodationTerm = (props) => {
    return (
        <tr>
            <td>{props.term.name}</td>
            <td>{props.term.category}</td>
            <td>{props.term.host.name}</td>
            <td>{props.term.numRooms}</td>   
            <td className={"text-right"}>
                <a title={"Delete"} className={"btn btn-danger"}
                   onClick={() => props.onDelete(props.term.id)}>
                    Delete
                </a>
                <Link className={"btn btn-info ml-2"}
                      onClick={() => props.onEdit(props.term.id)}
                      to={`/accommodations/edit/${props.term.id}`}>
                    Edit
                </Link>
                <a title={"RentRoom"} className={"btn btn-success"}
                   onClick={() => props.onRentRoom(props.term.id)}>
                    Rent a Room
                </a>
                <a title={"RentAccommodation"} className={"btn btn-success"}
                   onClick={() => props.onRentAccommodation(props.term.id)}>
                    Rent Accommodation
                </a>
            </td>
        </tr>
      );
}
 
export default AccommodationTerm;