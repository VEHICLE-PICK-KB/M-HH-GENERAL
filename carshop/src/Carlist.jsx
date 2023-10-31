import { useEffect, useState } from "react";
import { AgGridReact } from "ag-grid-react";
import Button from '@mui/material/Button'
import AddCar from "./AddCar";
import EditCar from "./EditCar";

import "ag-grid-community/styles/ag-grid.css";
import "ag-grid-community/styles/ag-theme-material.css";

function Carlist() {
  const [cars, setCars] = useState([]);

  useEffect(() => {
    fetchCars();
  }, []);

  const [columnDefs] = useState([
    { field: 'brand', sortable: true, filter: true },
    { field: 'model', sortable: true, filter: true },
    { field: 'color', sortable: true, filter: true },
    { field: 'year', sortable: true, filter: true, width: 100 },
    { field: 'fuel', sortable: true, filter: true, width: 110 },
    { field: 'price', sortable: true, filter: true, width: 120 },
    {
      cellRenderer: params => <EditCar fetchCars={fetchCars} data={params.data} />,
      width: 120
    },
    {
      cellRenderer: params => 
        <Button size="small" onClick={() => deleteCar(params.data._links.car.href)}>
          Delete
        </Button>,
      width: 120
    }
  ]);

  const fetchCars = () => {
    fetch(import.meta.env.VITE_API_URL + '/cars')
    .then(response => {
      if (response.ok)
        return response.json();
      else
        throw new Error("Error in fetch:" + response.statusText);
    })
    .then(data => setCars(data._embedded.cars))
    .catch(err => console.error(err))
  }

  const deleteCar = (url) => {
    if (window.confirm("Are you sure?")) {
      fetch(url, { method: 'DELETE' })
      .then(response => {
        if (response.ok)
          fetchCars();
        else
          throw new Error("Error in DELETE: " + response.statusText);
      })
      .catch(err => console.error(err))
      }
  }

  return(
    <>
      <AddCar fetchCars={fetchCars} />
      <div className="ag-theme-material" style={{ width: 1250, height: 500 }} >
        <AgGridReact 
          rowData={cars}
          columnDefs={columnDefs}
          pagination={true}
          paginationAutoPageSize={true}
        />
      </div>
    </>
  )
}

export default Carlist;