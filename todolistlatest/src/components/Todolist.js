import React from 'react';
import { useRef } from "react";
import '../App.css';
import { AgGridReact } from "ag-grid-react";

import "ag-grid-community/styles/ag-grid.css";
import "ag-grid-community/styles/ag-theme-material.css";
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import Stack from '@mui/material/Stack';
import { LocalizationProvider, DatePicker } from '@mui/x-date-pickers';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs'
import dayjs from 'dayjs';

function App() {
  const [todo, setTodo] = React.useState({desc:'', date:'', priority:''}); 
  const [todos, setTodos] = React.useState([]);
  const gridRef = useRef();
  const inputChanged = (event) => {
    setTodo({...todo, [event.target.name]: event.target.value});
  }

  const addTodo = (event) => {
    event.preventDefault();
    setTodos([...todos, todo]);
    setTodo({ desc: '', date: '', priority:'' });
  }

  const handleDate = (date) => {
    const formattedDate = date.format("DD-MM-YYYY")
    setTodo({ ...todo, date: formattedDate });
  };

  const deletE = () => {
    if (gridRef.current.getSelectedNodes().length > 0) {
      setTodos(todos.filter((todo, index) =>
      index != gridRef.current.getSelectedNodes()[0].id))
      }
      else {
      alert('Select a row first');
      }      
  };
  const columns = [
    {headerName: 'Date', field: 'date', sortable: 'true', filter: 'true', floatingFilter: 'true'},
    {headerName: 'Description', field: 'desc', sortable: 'true', filter: 'true', floatingFilter: 'true'},
    {headerName: 'Priority', field: 'priority', sortable: 'true', filter: 'true', floatingFilter: 'true',
    cellStyle: params => params.value === "High" ? {color: 'red'} : {color: 'black'}}
  ]

  return (
    <div className="App">
      <header className='App-header'>
        <p>
          Todolist-sovellus
        </p>
      </header>
      <div>
      <Stack direction="row" spacing={2} justifyContent="center" alignItems="center">
      <LocalizationProvider dateAdapter={AdapterDayjs}>
      <DatePicker label="Date" value={dayjs(todo.date, "DD-MM-YYYY")} onChange={handleDate} format="DD-MM-YYYY" slotProps={{ textField: { variant: 'standard', error: false } }}  />
      </LocalizationProvider>
      <TextField label="Description" variant="standard" name="desc" value={todo.desc} onChange={inputChanged}/>
      <TextField label="Priority" variant="standard" name="priority" value={todo.priority} onChange={inputChanged}/>
      <Button onClick={addTodo} variant="contained" color="success">Add</Button>
      <Button onClick={deletE} variant="contained" color="error">Delete</Button>
      </Stack>   
      <div className="ag-theme-material" style={{ height: '700px', width: '52%', margin: 'auto' }}>
      <AgGridReact 
      rowData={todos} 
      columnDefs={columns}
      rowSelection="single"
      ref={gridRef}
      onGridReady={ params => gridRef.current = params.api }
      animateRows={true}>
      </AgGridReact> 
      </div> 
    </div>
      </div>
  );
}
export default App;