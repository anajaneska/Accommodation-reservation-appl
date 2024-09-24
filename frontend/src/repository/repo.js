import axios from '../custom-axios/axios';

const AppService = {
    fetchAccommodations: () => {
        return axios.get("/accommodations");
    },
    fetchHosts:() => {
        return axios.get("/hosts");
    },
    fetchCategories: () => {
        return axios.get("/categories")
    },
    deleteAccommodation: (id) => {
        return axios.delete(`/accommodations/delete/${id}`);
    },
    addAccommodation: (name, category, host, numRooms) => {
        return axios.post("/accommodations/add",{
            "name": name,
            "category": category,
            "host": host,
            "numRooms": numRooms
        });
    },
    editAccommodation: (id, name, category, host, numRooms) => {
        return axios.put(`/accommodations/edit/${id}`,{
            "name": name,
            "category": category,
            "host": host,
            "numRooms": numRooms
        });
    },
    getAccommodation: (id) => {
        return axios.get(`/accommodations/${id}`);
    },
    rentAccommodation: (id) => {
        return axios.post(`/accommodations/toggle-rented/${id}`);
    },
    rentRoom: (id) => {
        return axios.post(`/accommodations/rent-room/${id}`);
    }
}
export default AppService;