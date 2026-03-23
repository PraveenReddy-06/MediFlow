import Navbar from "../components/Navbar";
import { memo } from 'react';
import Card from '../components/Card'

const LandingPage = () => {
  return (
    <div className="h-screen w-screen">
      <Navbar /> 

      <div className="flex h-3/4 bg-blue-300">
        <div className="w-1/2 flex justify-center items-center text-4xl">
            <a href="/bookAppointment">Book Appointment</a>
        </div>
       
        <div className="w-1/2 flex flex-col justify-center items-center text-center text-4xl">
            <h1>Welcome To <span className="text-6xl">MediFlow</span></h1>
            <h2>No More Waiting For Consultation</h2>
        </div>       
      </div>

    
      <div className="text-2xl p-5 overflow-x-auto scrollbar-hide ">
        <div className="mb-5">
            Top Searched Specialties
        </div>
        <div className=" flex gap-10">
            <Card />
            <Card />
            <Card />
            <Card />
            <Card />
            <Card />
            <Card />
            <Card />
        </div>
      </div>

      <div className="p-5 text-2xl">
        <h2>Select Your Problem By Clicking On Body Parts</h2>
      </div>
          
    </div>
  );
};

export default memo(LandingPage);