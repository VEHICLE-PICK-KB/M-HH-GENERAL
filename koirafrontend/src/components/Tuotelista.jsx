import CircularProgress from "@mui/material/CircularProgress";
import Typography from "@mui/material/Typography";
import "ag-grid-community/styles/ag-grid.css";
import "ag-grid-community/styles/ag-theme-alpine.css";
import { AgGridReact } from "ag-grid-react";
import { useEffect, useRef, useState } from "react";
import Rekisterointi from "./Rekisterointi";
import PoistaKayttaja from "./PoistaKayttaja";
import "./Tuotelista.css";

const Tuotelista = () => {
  const [tuotteet, setTuotteet] = useState([]);

  useEffect(() => {
    fetchTuotteet();
  }, []);

  const capitalizeFirstLetter = (value) => {
    if (!value) return value;
    return value.charAt(0).toUpperCase() + value.slice(1).toLowerCase();
  };

  const [columnDefs] = useState([
    {
      field: "tuoteNimi",
      headerName: "Tuotenimi",
      sortable: true,
      filter: "agTextColumnFilter",
    },
    {
      field: "tyyppi",
      headerName: "Tyyppi",
      sortable: true,
      filter: "agTextColumnFilter",
      valueFormatter: (params) => capitalizeFirstLetter(params.value),
    },
    {
      field: "vari",
      headerName: "Väri",
      sortable: true,
      filter: "agTextColumnFilter",
    },
    {
      field: "koko",
      headerName: "Koko",
      sortable: true,
      filter: "agTextColumnFilter",
    },
    {
      field: "hinta",
      headerName: "Hinta",
      sortable: true,
      filter: "agNumberColumnFilter",
      valueFormatter: (params) => {
        const formatter = new Intl.NumberFormat("fi-FI", {
          style: "currency",
          currency: "EUR",
        });
        return formatter.format(params.value);
      },
    },
    {
      field: "valmistaja.nimi",
      headerName: "Valmistaja",
      sortable: true,
      filter: "agTextColumnFilter",
    },
  ]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const gridRef = useRef();

  const fetchTuotteet = () => {
    fetch("https://softala.haaga-helia.fi:8075/api/tuotteet")
      .then((response) => {
        if (!response.ok)
          throw new Error("Something went wrong: " + response.statusText);
        return response.json();
      })
      .then((data) => {
        setTuotteet(data);
        setLoading(false);
      })
      .catch((err) => {
        setError(err);
        setLoading(false);
      });
    if (loading) {
      return (
        <div className="loading-container">
          <CircularProgress />
        </div>
      );
    }

    if (error) {
      return (
        <Typography className="error-message" color="error">
          {`Error: ${error.message}`}
        </Typography>
      );
    }
  };

  return (
    <>
      <div className="ag-theme-alpine grid-container">
        <Typography className="title" variant="h4" component="h1" gutterBottom>
          Tuotteet
        </Typography>
        <Rekisterointi fetchTuotteet={fetchTuotteet} />
        <PoistaKayttaja fetchTuotteet={fetchTuotteet} />
        
        <AgGridReact
          rowData={tuotteet}
          columnDefs={columnDefs}
          defaultColDef={{
            flex: 1,
            minWidth: 100,
            filter: true,
            resizable: true,
          }}
          Ref={gridRef}
          className="grid-full-width"
        />
      </div>
    </>
  );
};

export default Tuotelista;
