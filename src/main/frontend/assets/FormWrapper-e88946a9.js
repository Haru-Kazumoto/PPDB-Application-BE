import{aW as p,e as d,j as n,aX as x}from"./index-21f1c136.js";const b=({onSubmit:t,id:a,initialValues:o,children:r,method:s="onSubmit",width:m="100%",noValidate:f=!0})=>{const e=p({defaultValues:o,mode:s});return d.useEffect(()=>{if(o)for(const[u,c]of Object.entries(o))e.setValue(u,c)},[o]),n.jsx(x,{...e,children:n.jsx("form",{id:a,style:{width:m},onSubmit:e.handleSubmit(t),onChange:s==="onChange"?e.handleSubmit(t):void 0,noValidate:f,children:typeof r=="function"?r(e):r})})};export{b as F};