import './App.css';
import React, {Component} from "react";
import {BrowserRouter as Router, Redirect, Route, Routes} from 'react-router-dom'
import AppService from '../../repository/repo';
import Accommodations from '../Accommodations/AccommodationList/accommodations';
import Categories from '../Categories/categories';
import Header from '../Header/header';
import AccommodationAdd from '../Accommodations/AccommodationAdd/accommodationAdd';
import AccommodationEdit from '../Accommodations/AccommodationEdit/accommodationEdit'
import AccommodationsRented from '../Accommodations/AccommodationList/accommodationsRented';

class App extends Component{
  constructor(props){
    super(props);
    this.state = {
      accommodations: [],
      categories: [],
      hosts: [],
      selectedAccommodation: {}
    }
  }

  render(){
    return (
      <Router>
        <Header/>
        <Routes>
        <Route path="/accommodations" element={<Accommodations accommodations={this.state.accommodations} onDelete={this.deleteAccommodation} onEdit={this.getAccommodation} onRentRoom={this.rentRoom} onRentAccommodation={this.rentAccommodation}/>} ></Route>
        <Route path="/accommodations/rented" element={<AccommodationsRented accommodations={this.state.accommodations} onDelete={this.deleteAccommodation} onEdit={this.getAccommodation} onRentRoom={this.rentRoom} onRentAccommodation={this.rentAccommodation}/>} ></Route>
        <Route path="/" element={<Accommodations accommodations={this.state.accommodations} onDelete={this.deleteAccommodation} onEdit={this.getAccommodation} onRentRoom={this.rentRoom} onRentAccommodation={this.rentAccommodation}/>} ></Route>
        <Route path="/categories" element={<Categories categories={this.state.categories}/>} ></Route>
        <Route path="/accommodations/add" element={<AccommodationAdd categories={this.state.categories} hosts={this.state.hosts} onAddAccommodation={this.addAccommodation}/>} ></Route>
        <Route path="/accommodations/edit/:id" element={<AccommodationEdit categories={this.state.categories} hosts={this.state.hosts} onEditAccommodation={this.editAccommodation} accommodation={this.state.selectedAccommodation}/>} ></Route>
        </Routes>
      </Router>
    );
  }

  componentDidMount() {
    this.fetchData()
  }
  fetchData = () => {
    this.loadAccommodations();
    this.loadCategories();
    this.loadHosts();
  }
  loadAccommodations = () => {
    AppService.fetchAccommodations()
    .then((data)=>{
      this.setState({
        accommodations: data.data
      })
    });
  }
  loadCategories = () => {
    AppService.fetchCategories()
    .then((data)=>{
      this.setState({
        categories: data.data
      })
    });
  }
  loadHosts = () => {
    AppService.fetchHosts()
    .then((data)=>{
      this.setState({
        hosts: data.data
      })
    });
  }
  deleteAccommodation = (id) => {
    AppService.deleteAccommodation(id)
    .then(()=> {
      this.loadAccommodations();
    });
  }
  rentRoom = (id) => {
    AppService.rentRoom(id)
    .then(()=>{
      this.loadAccommodations();
    })
  }
  rentAccommodation = (id) => {
    AppService.rentAccommodation(id)
    .then(()=>{
      this.loadAccommodations();
    })
  }
  addAccommodation = (name, category, host, numRooms) => {
    AppService.addAccommodation(name, category, host, numRooms)
    .then(() => {
      this.loadAccommodations();
    });
  }
  editAccommodation = (id, name, category, host, numRooms) => {
    AppService.editAccommodation(id,name, category, host, numRooms)
    .then(()=>{
      this.loadAccommodations();
    });
  }
  getAccommodation = (id) =>{
    AppService.getAccommodation(id)
    .then((data)=>{
      this.setState({
        selectedAccommodation: data.data
      })
    })
  }
}

export default App;
