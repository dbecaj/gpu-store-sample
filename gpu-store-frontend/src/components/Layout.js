import './Layout.css';

export default function Layout(props) {
  return (
    <div class="layout">
      {props.children}  
    </div>
  )
}